import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListProductOperationsComponent } from './list-product-operations.component';

describe('ListProductOperationsComponent', () => {
  let component: ListProductOperationsComponent;
  let fixture: ComponentFixture<ListProductOperationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListProductOperationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListProductOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
