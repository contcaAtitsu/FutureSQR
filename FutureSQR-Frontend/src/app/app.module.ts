import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MainPageComponent } from './views/main-page/main-page.component';
import { StarredProjectsComponent } from './views/main-page/starred-projects/starred-projects.component';
import { MostActiveProjectsComponent } from './views/main-page/most-active-projects/most-active-projects.component';

@NgModule({
  declarations: [
    AppComponent,
    MainPageComponent,
    StarredProjectsComponent,
    MostActiveProjectsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
