import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeadlineButtonComponent } from './deadline-button.component';

describe('DeadlineButtonComponent', () => {
  let component: DeadlineButtonComponent;
  let fixture: ComponentFixture<DeadlineButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeadlineButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeadlineButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
