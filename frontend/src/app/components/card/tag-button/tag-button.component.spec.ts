import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TagButtonComponent } from './tag-button.component';

describe('TagButtonComponent', () => {
  let component: TagButtonComponent;
  let fixture: ComponentFixture<TagButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TagButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TagButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
