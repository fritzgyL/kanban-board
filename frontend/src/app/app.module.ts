import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { BoardComponent } from './board/board.component';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component'
import { BoardService } from './services/board-service.service';
import { SectionComponent } from './section/section.component';
import { CardComponent } from './card/card.component';
import { FormsModule } from '@angular/forms';
import { CardService } from './services/card-service.service';
import { SectionService } from './services/section.service';
import { CardDetailsComponent } from './card-details/card-details.component';
import { CardDescriptionComponent } from './card-description/card-description.component';
import { CardTitleComponent } from './card-title/card-title.component';
import { CardDetailsButtonsComponent } from './card-details-buttons/card-details-buttons.component';

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
    SectionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
