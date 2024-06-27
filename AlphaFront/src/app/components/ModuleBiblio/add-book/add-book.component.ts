import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BookService } from 'src/app/Services/ModuleBiblio/book.service';
import { Book } from 'src/app/models/Book';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent {
  addForm!:FormGroup;
  selectedFile!: File;
  BookRessource!:File;
  book!: Book;
  constructor(private fb:FormBuilder, private bookService:BookService, private router:Router){
    this.addForm = this.fb.group({
      author:['',Validators.required],
      title:['',Validators.required],
      description:['',Validators.required],
      type:['',Validators.required],
      date:['',Validators.required],
      file:['',Validators.required],
      res:['',Validators.required]
    }),
    this.book = {
      id: undefined,
      auteur:'test',
      title:'test',
      description:'test',
      type:'test',
      date:new Date(),
      cover :'',
      file:""
      }
  }
  
  onFileSelected(event: any) {
    const file = event.target.files[0];
    console.log(file);
    if (file) {
      this.selectedFile = file;
    }
  }

  onFileSelectedRessource(event: any) {
    const file = event.target.files[0];
    console.log(file);
    if (file) {
      this.BookRessource = file;
    }
  }

  onSubmit(){
    this.book.auteur = this.addForm.value.author;
    this.book.title = this.addForm.value.title;
    this.book.description = this.addForm.value.description;
    this.book.type = this.addForm.value.type;
    this.book.date = this.addForm.value.date;
    
    this.bookService.addCover(this.selectedFile).subscribe((data)=>{
      this.book.cover = data
      console.log("succes adding cover")
      this.bookService.addBookFile(this.BookRessource).subscribe((data)=>{
        console.log(data);
        this.book.file = "/"+this.BookRessource.name;
        this.bookService.addBook(this.book).subscribe((data)=>{
          console.log(data);
          this.router.navigate(['/books']);
        })
      })
    })
  }

  
}
