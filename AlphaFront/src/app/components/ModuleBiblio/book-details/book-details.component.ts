import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BookService } from 'src/app/Services/ModuleBiblio/book.service';
import { Book } from 'src/app/models/Book';
import { Comment } from 'src/app/models/Comment';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {
  // Comment Form 
  commentForm: FormGroup;
  //stars: number[] = [1, 2, 3, 4, 5];
  public stars: boolean[] = Array(5).fill(false);
  comment! : Comment;
  comments : Comment[] = [];

  rating: number = 0;
  public rate(rating: number) {
    console.log('rating', rating);
    this.stars = this.stars.map((_, i) => rating > i);
    console.log('stars', this.stars);
  }

  book!:Book;

  constructor(private bookService:BookService, private route:ActivatedRoute, private formBuilder:FormBuilder,private router:Router){
    this.bookService.showBook(this.route.snapshot.params['id']).subscribe((data)=>{
      this.book = data;
      this.bookService.getComments(this.route.snapshot.params['id']).subscribe((list)=>{
        this.comments  = list;
        this.calculateRating();
        console.log('Comment List ', list);
        console.log('rating', this.rating)
      })
    });

    this.commentForm = this.formBuilder.group({
      content: ['', Validators.required],
      rating: [0, Validators.required]
    });

    
  }

  downloadBook(){
    this.bookService.downloadBook(this.book.file).subscribe((data)=>{
      
          const blob = new Blob([data], { type: 'application/octet-stream' });
          const url = window.URL.createObjectURL(blob);
          
          const a = document.createElement('a');
          a.href = url;
          a.download = this.book.file;  // You can set the desired file name here
          document.body.appendChild(a); // Append to the document body
          a.click();
          document.body.removeChild(a); // Remove it after the download is triggered
          window.URL.revokeObjectURL(url);
        },
        (error) => {
          console.error('Error downloading file', error);
        }
      );
  }


  setRating(): number {
    const trueCount = this.stars.filter(star => star).length;
    console.log('Number of true values:', trueCount);
    this.commentForm.patchValue({ rating: trueCount });
    return trueCount;
  }


  onSubmit(): void {
    this.comment = {
      id : 0,
      content : "",
      date : new Date(),
      user : undefined,
      book : this.book,
      rate:0
    };
    
    if (this.commentForm.valid) {
      this.commentForm.patchValue({ rating: this.setRating() });
      //const comment = this.commentForm.value;
      //console.log('Comment:', comment);
      this.comment.rate = this.commentForm.get('rating')?.value;
      this.comment.content = this.commentForm.get('content')?.value;
      this.bookService.addComment(this.comment).subscribe((data)=>{
          console.log(data);
          this.router.navigate(['/book-details',this.route.snapshot.params['id']]);
      })
      //console.log("stars : ",this.setRating());
      // Handle form submission, e.g., send the comment to a server
    } else {
      console.error('Form is invalid');
    }
  }


  getStarsArray(rate: number){
    return Array.from({ length: 5 }, (_, i) => i < rate);
  }

  setTotalStarsArray(rate:number){
    
    return Array.from({ length: 5}, (_, i) => i<rate)
  }

  calculateRating(){
    
    this.comments.forEach((c)=>{
      this.rating += c.rate;
    })
    this.rating = this.rating / this.comments.length
  }
  
}
