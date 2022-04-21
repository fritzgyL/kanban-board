import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardTagComponent } from './card-tag.component';

describe('CardTagComponent', () => {
  let component: CardTagComponent;
  let fixture: ComponentFixture<CardTagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardTagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardTagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
