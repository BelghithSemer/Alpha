import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OffersComponent } from './components/ModuleEmploi/offers/offers.component';
import { AddOfferComponent } from './components/ModuleEmploi/add-offer/add-offer.component';
import { EditOfferComponent } from './components/ModuleEmploi/edit-offer/edit-offer.component';
import { OfferDetailsComponent } from './components/ModuleEmploi/offer-details/offer-details.component';
import { PostulerComponent } from './components/ModuleEmploi/postuler/postuler.component';
import { CandidaciesComponent } from './components/ModuleEmploi/candidacies/candidacies.component';
import { CoursesComponent } from './components/ModuleCours/courses/courses.component';
import { CourseDetailsComponent } from './components/ModuleCours/course-details/course-details.component';
import { AddCourseComponent } from './components/ModuleCours/add-course/add-course.component';
import { EditCourseComponent } from './components/ModuleCours/edit-course/edit-course.component';
import { AddDocumentComponent } from './components/ModuleCours/add-document/add-document.component';
import { BooksComponent } from './components/ModuleBiblio/books/books.component';
import { AddBookComponent } from './components/ModuleBiblio/add-book/add-book.component';
import { BookDetailsComponent } from './components/ModuleBiblio/book-details/book-details.component';
import { AddReclamationComponent } from './components/ModuleBiblio/add-reclamation/add-reclamation.component';
import { ReclamationComponent } from './components/ModuleBiblio/reclamation/reclamation.component';
import { EditReclamationComponent } from './components/ModuleBiblio/edit-reclamation/edit-reclamation.component';

const routes: Routes = [
  {path:'offers', component:OffersComponent},
  {path:'add-offer', component:AddOfferComponent},
  { path: 'edit-offer/:id', component: EditOfferComponent },
  { path: 'offer-details/:id', component: OfferDetailsComponent },
  { path: 'postuler/:id', component: PostulerComponent },
  { path: 'candidacies/:id', component: CandidaciesComponent },
  { path: 'courses', component: CoursesComponent },
  { path: 'course-details/:id', component: CourseDetailsComponent },
  { path: 'add-course', component: AddCourseComponent },
  { path: 'edit-course/:id', component: EditCourseComponent },
  { path: 'add-document/:id', component: AddDocumentComponent },
  { path: 'books', component: BooksComponent},
  { path: 'addbook', component: AddBookComponent},
  { path: 'book-details/:id', component: BookDetailsComponent},
  { path: 'add-reclamation', component: AddReclamationComponent},
  { path : 'reclamation', component: ReclamationComponent},
  { path : 'edit-rec/:id', component : EditReclamationComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 



}
