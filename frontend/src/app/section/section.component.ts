import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../class/section/section';

@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() section: Section = new Section();
  constructor() { }

  ngOnInit(): void {
  }

}
