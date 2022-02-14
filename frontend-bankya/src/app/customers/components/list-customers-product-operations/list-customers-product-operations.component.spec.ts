import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCustomersProductOperationsComponent } from './list-customers-product-operations.component';

describe('ListCustomersProductOperationsComponent', () => {
  let component: ListCustomersProductOperationsComponent;
  let fixture: ComponentFixture<ListCustomersProductOperationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCustomersProductOperationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCustomersProductOperationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
