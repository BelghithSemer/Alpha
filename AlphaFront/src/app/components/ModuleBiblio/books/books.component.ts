import { Component } from '@angular/core';
import { BookService } from 'src/app/Services/ModuleBiblio/book.service';
import { Book } from 'src/app/models/Book';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent {
books : Book[] = [];
recommendation : Book[] = [] ;
constructor(private bs:BookService){

}
ngOnInit(){
  this.bs.getAllBooks().subscribe((data)=>{
    this.books = data;
    console.log(this.books);
  })
  this.bs.getrecommendation().subscribe((data)=>{
    this.recommendation = data;
    console.log('recommandation : ',data);
  })
}
}
