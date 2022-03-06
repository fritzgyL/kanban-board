import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardLinkSectionComponent } from './card-link-section.component';

describe('CardLinkSectionComponent', () => {
  let component: CardLinkSectionComponent;
  let fixture: ComponentFixture<CardLinkSectionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardLinkSectionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardLinkSectionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
