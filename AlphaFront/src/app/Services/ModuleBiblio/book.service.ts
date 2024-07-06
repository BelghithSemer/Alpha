import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from 'src/app/models/Book';
import { Comment } from 'src/app/models/Comment';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http:HttpClient) { }

  getAllBooks(){
    return this.http.get<Book[]>('http://localhost:8089/livre/show');  
  }

  addCover(file:File){
    const formData: FormData = new FormData();
    formData.append('cover', file, file.name);
    return this.http.post(`http://localhost:8089/livre/addCover`, formData,{ responseType: 'text' });
  }

  addBookFile(file:File){
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('filePath',"test");
    return this.http.post(`http://localhost:8089/livre/uploadtwo`, formData,{ responseType: 'text' });
  }

  addBook(book:Book){
    return this.http.post(`http://localhost:8089/livre/add`, book);
  }

  showBook(id:number){
    return this.http.get<Book>(`http://localhost:8089/livre/show/${id}`);
  }

  downloadBook(path : string){
    /*const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/octet-stream'
      }),
      responseType: 'blob' as 'json'
    };

    return this.http.get(`http://localhost:8089/livre/download?path=${encodeURIComponent(path)}`, options);*/
    return this.http.get(`http://localhost:8089/livre/download?path=${path}`, { responseType: 'arraybuffer' });
    //return this.http.get(`http://localhost:8089/livre/download?path=${path}`);
  }


  addComment(comment: Comment){
    return this.http.post('http://localhost:8089/comment/add',comment);
  }
  
  getComments(id:number){
    return this.http.get<Comment[]>(`http://localhost:8089/comment/getByBook/${id}`);
  }

  getrecommendation(){
    return this.http.get<Book[]>('http://localhost:8089/livre/getRecomendation');
  }
  

}
