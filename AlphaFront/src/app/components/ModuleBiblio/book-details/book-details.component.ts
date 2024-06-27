import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BookService } from 'src/app/Services/ModuleBiblio/book.service';
import { Book } from 'src/app/models/Book';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.css']
})
export class BookDetailsComponent {

  book!:Book;

  constructor(private bookService:BookService, private route:ActivatedRoute){
    this.bookService.showBook(this.route.snapshot.params['id']).subscribe((data)=>{
      this.book = data;
    })
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
}
