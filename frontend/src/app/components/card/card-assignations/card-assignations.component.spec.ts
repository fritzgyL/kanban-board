import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardAssignationsComponent } from './card-assignations.component';

describe('CardAssignationsComponent', () => {
  let component: CardAssignationsComponent;
  let fixture: ComponentFixture<CardAssignationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardAssignationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardAssignationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
