import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-container',
  templateUrl: './error-container.component.html',
  styleUrls: ['./error-container.component.css']
})
export class ErrorContainerComponent implements OnInit {

  @Input() message: string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
