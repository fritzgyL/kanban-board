import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-success-container',
  templateUrl: './success-container.component.html',
  styleUrls: ['./success-container.component.css']
})
export class SuccessContainerComponent implements OnInit {
  @Input() message: string = '';

  constructor() { }

  ngOnInit(): void {
  }

}
