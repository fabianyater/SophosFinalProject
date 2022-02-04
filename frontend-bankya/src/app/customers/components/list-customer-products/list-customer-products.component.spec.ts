import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListCustomerProductsComponent } from './list-customer-products.component';

describe('ListCustomerProductsComponent', () => {
  let component: ListCustomerProductsComponent;
  let fixture: ComponentFixture<ListCustomerProductsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListCustomerProductsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListCustomerProductsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
