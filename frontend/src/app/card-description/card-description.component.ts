import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-card-description',
  templateUrl: './card-description.component.html',
  styleUrls: ['./card-description.component.css']
})
export class CardDescriptionComponent implements OnInit {

  @Input() description = ''
  isWriting: boolean = false
  constructor() { }

  ngOnInit(): void {
  }

  onUpdateDescription() {
    this.isWriting = true;
  }

}
