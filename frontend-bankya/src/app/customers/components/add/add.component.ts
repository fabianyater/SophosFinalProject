import { Router } from '@angular/router';
import { CustomerService } from '../../services/customer.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { CustomerModel } from '../../models/customer.model';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css'],
})
export class AddComponent implements OnInit {
  datePipe: DatePipe = new DatePipe('en-En');
  formattedDate = this.datePipe.transform(
    new Date(),
    'yyyy-MM-dd HH:mm:ss.SSS'
  );
  submitted: boolean = false;
  registerForm!: FormGroup;
  public documents = [
    { type: 'Cédula de ciudadanía' },
    { type: 'Cédula Extranjería' },
    { type: 'Pasaporte' },
  ];

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      customer_name: ['', Validators.required],
      customer_lastname: ['', Validators.required],
      customer_email: ['', Validators.required, Validators.email],
      customer_document_type: ['', Validators.required],
      customer_document_number: ['', Validators.required],
      customer_birthday: ['', Validators.required],
      customer_created_at: this.formattedDate,
    });
  }

  get form() {
    return this.registerForm.controls;
  }

  onSubmit() {
    this.submitted = true;
    if (this.registerForm.invalid) {
      return;
    }
    this.customerService.addCustomer(this.registerForm.value).subscribe();
    //this.router.navigateByUrl('/customers')
    window.location.reload();
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
