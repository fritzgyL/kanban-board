import { Component, Input, OnInit } from '@angular/core';
import { Assignation } from 'src/app/models/assignation/assignation';

@Component({
  selector: 'app-card-assignations',
  templateUrl: './card-assignations.component.html',
  styleUrls: ['./card-assignations.component.css']
})
export class CardAssignationsComponent implements OnInit {
  @Input() assignations: Assignation[] = [];
  selectedAssigneeFullName: string = '';

  constructor() { }

  ngOnInit(): void {
  }

  onClickOnAvatar(event: any) {
    this.selectedAssigneeFullName = event.sourceId;
  }

}
