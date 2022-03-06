import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDetailsButtonsComponent } from './card-details-buttons.component';

describe('CardDetailsButtonsComponent', () => {
  let component: CardDetailsButtonsComponent;
  let fixture: ComponentFixture<CardDetailsButtonsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardDetailsButtonsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardDetailsButtonsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
