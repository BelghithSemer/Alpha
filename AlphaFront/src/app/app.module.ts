import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/shared/navbar/navbar.component';
import { OffersComponent } from './components/ModuleEmploi/offers/offers.component';
import { ReactiveFormsModule } from '@angular/forms';
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

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    OffersComponent,
    AddOfferComponent,
    EditOfferComponent,
    OfferDetailsComponent,
    PostulerComponent,
    CandidaciesComponent,
    CoursesComponent,
    CourseDetailsComponent,
    AddCourseComponent,
    EditCourseComponent,
    AddDocumentComponent,
    BooksComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
