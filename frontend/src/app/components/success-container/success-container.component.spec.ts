import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessContainerComponent } from './success-container.component';

describe('SuccessContainerComponent', () => {
  let component: SuccessContainerComponent;
  let fixture: ComponentFixture<SuccessContainerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SuccessContainerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessContainerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
