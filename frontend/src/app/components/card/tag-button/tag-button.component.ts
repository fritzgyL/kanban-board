import { Component, Input, OnInit } from '@angular/core';
import { Tag } from 'src/app/models/tag/tag';

@Component({
  selector: 'app-tag-button',
  templateUrl: './tag-button.component.html',
  styleUrls: ['./tag-button.component.css']
})
export class TagButtonComponent implements OnInit {
  @Input() tag: Tag = new Tag();

  constructor() { }

  ngOnInit(): void {
  }

}
