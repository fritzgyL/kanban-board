import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardModalTitleComponent } from './card-modal-title.component';

describe('CardModalTitleComponent', () => {
  let component: CardModalTitleComponent;
  let fixture: ComponentFixture<CardModalTitleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardModalTitleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardModalTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
