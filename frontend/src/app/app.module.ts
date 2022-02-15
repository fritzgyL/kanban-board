import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BoardComponent } from './components/board/board.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './components/dashboard/dashboard.component'
import { BoardService } from './services/board/board-service.service';
import { SectionComponent } from './components/section/section.component';
import { CardComponent } from './components/card/card.component';
import { FormsModule } from '@angular/forms';
import { CardService } from './services/card/card-service.service';
import { SectionService } from './services/section/section.service';
import { CardDescriptionComponent } from './components/card/card-description/card-description.component';
import { CardTitleComponent } from './components/card/card-title/card-title.component';
import { CardDetailsButtonsComponent } from './components/card/card-details-buttons/card-details-buttons.component';
import { CardDetailsComponent } from './components/card/card-details/card-details.component';
import { CardResourcesComponent } from './components/card/card-resources/card-resources.component';
import { DeadlineButtonComponent } from './components/deadline-button/deadline-button.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BoardComponent,
    DashboardComponent,
    SectionComponent,
    CardComponent,
    CardDetailsComponent,
    CardDescriptionComponent,
    CardTitleComponent,
    CardDetailsButtonsComponent,
    CardResourcesComponent,
    DeadlineButtonComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule

  ],
  providers: [
    BoardService,
    CardService,
    SectionService,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
