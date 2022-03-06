import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardResourcesComponent } from './card-resources.component';

describe('CardResourcesComponent', () => {
  let component: CardResourcesComponent;
  let fixture: ComponentFixture<CardResourcesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardResourcesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardResourcesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
