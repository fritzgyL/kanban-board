import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDateStruct } from '@ng-bootstrap/ng-bootstrap';
import { CardService } from 'src/app/services/card/card-service.service';
import { CustomDatePickerAdapter } from 'src/app/services/date-formatter.service';
import { Output, EventEmitter } from '@angular/core';
@Component({
  selector: 'app-card-date',
  templateUrl: './card-date.component.html',
  styleUrls: ['./card-date.component.css']
})

export class CardDateComponent implements OnInit {
  @Output() updateDate = new EventEmitter<string>();
  selectedDate1: NgbDateStruct | null = null;
  selectedDate2: string | null = null;

  date: { year: number, month: number } | null = null;


  constructor(private calendar: NgbCalendar, private dateAdapter: CustomDatePickerAdapter, private cardService: CardService) { }

  ngOnInit(): void {
    this.selectToday();
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
