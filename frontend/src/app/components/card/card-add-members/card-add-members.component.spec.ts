import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardAddMembersComponent } from './card-add-members.component';

describe('CardAddMembersComponent', () => {
  let component: CardAddMembersComponent;
  let fixture: ComponentFixture<CardAddMembersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardAddMembersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardAddMembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
