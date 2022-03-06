import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { CardService } from 'src/app/services/card/card-service.service';
import { CustomDatePickerAdapter } from 'src/app/services/date-formatter.service';
import { Output, Input, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-card-date',
  templateUrl: './card-date.component.html',
  styleUrls: ['./card-date.component.css']
})

export class CardDateComponent implements OnInit {
  @Output() updateDate = new EventEmitter<string>();
  @Input() operation = '';
  @Input() cardDeadline = '';


  selectedDate1: NgbDateStruct | null = null;
  selectedDate2: string | null = null;

  date: { year: number, month: number } | null = null;


  constructor(private calendar: NgbCalendar, private dateAdapter: CustomDatePickerAdapter, private cardService: CardService) { }

  ngOnInit(): void {
    if (this.operation == 'create') {
      this.selectToday();
    }
    else if (this.operation == 'update') {
      this.setCalendarFromCardDeadline();
    }
  }

  private setCalendarFromCardDeadline() {
    this.selectedDate1 = this.dateAdapter.fromModel(this.cardDeadline);
    this.selectedDate2 = this.cardDeadline;
  }

  private selectToday() {
    this.selectedDate1 = this.calendar.getToday();
    this.selectedDate2 = this.dateAdapter.toModel(this.selectedDate1);
  }

  onDateSelect(selectedDate: any) {
    this.selectedDate1 = selectedDate;
    this.selectedDate2 = this.dateAdapter.toModel(this.selectedDate1);
  }

  onInputDateChange(event: Event): void {
    const target = event.target as HTMLTextAreaElement;
    this.selectedDate1 = this.dateAdapter.parse(target.value);
  }

  onSaveDate() {
    this.updateDate.emit(this.selectedDate2!);
  }

}
