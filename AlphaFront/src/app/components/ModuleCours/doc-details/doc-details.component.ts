import { HttpClient } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { SignaturePad } from 'angular-signature-pad-v2';
import { CoursesService } from 'src/app/Services/ModuleCourses/courses.service';
import { Document } from 'src/app/models/Document';
import { cours } from 'src/app/models/cours';

@Component({
  selector: 'app-doc-details',
  templateUrl: './doc-details.component.html',
  styleUrls: ['./doc-details.component.css']
})
export class DocDetailsComponent {
  cours:cours = new cours();
    id!:number;
    docId!:number;
    document: Document = new Document();
    //documents:Document[] = [];
    signatureImage: string = '';
    @ViewChild('signaturePad') signaturePad!: SignaturePad;

  signaturePadOptions: Object = {
    'minWidth': 1,
    'canvasWidth': 400,
    'canvasHeight': 200
  };
  documentSrc: any;
  documentContent!: string;
  isPdf: boolean = false;
  isText: boolean = false;
  


    constructor(private cs:CoursesService,private route:ActivatedRoute,private http: HttpClient, private sanitizer: DomSanitizer){
      
    }
    ngOnInit(){
      const idString = this.route.snapshot.paramMap.get('id');
      if (idString !== null) {
        this.id = +idString;
        if (!isNaN(this.id)) {
          this.getCoursById(this.id);
        } else {
          console.error('Invalid course id');
        }
      } else {
          console.error('No course id provided');
        }

        // getting the document 
        const idStr = this.route.snapshot.paramMap.get('docId');
      if (idStr !== null) {
        this.docId = +idStr;
        if (!isNaN(this.docId)) {
          this.cs.getDocumentById(this.docId).subscribe((data)=>{
            this.document = data;
            if (this.document.signature) {
              this.signaturePad.fromDataURL(this.document.signature);
            } else {
              console.error('Signature data is missing');
            }
            console.log(this.document);
          })
        } else {
          console.error('Invalid document id');
        }
      } else {
          console.error('No course id provided');
        }
       
        
        
        
        
    }
    getCoursById(id: number): void {
      this.cs.getCoursById(id).subscribe(
        (data: cours) => {
          this.cours = data;
          console.log(this.cours)
          
        },
        (error) => {
          console.error('Error fetching offer:', error);
        }
      );
    }

    clearSignature() {
      this.signaturePad.clear();
    }
  
    saveSignature() {
      const signatureData = this.signaturePad.toDataURL();
      const documentId = this.docId; // Use the actual document ID
    
      this.http.post(
        `http://localhost:8089/document/${documentId}/signature`, 
        { signatureData },
        { responseType: 'text' } // Expecting a plain text response
      ).subscribe(
        response => {
          console.log('Signature saved successfully:', response);
          // Handle success if needed
        },
        error => {
          console.error('Error saving signature', error);
          // Handle error appropriately
        }
      );
    }
    

    

}
