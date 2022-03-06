import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  copyrightSymbol = "©";
  creationDate = " 2022 - ";
  currentDate: number = 0;
  ownerShip = " Fritzgy Lubin - All Rights Reserved. "

  constructor() {

  }

  ngOnInit(): void {
    this.currentDate = this.getCurrentDate();
  }

  getCurrentDate(): number {
    return new Date().getFullYear();
  }

}
