import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ClientModel } from 'src/app/models/client-model';
import { ClientServiceService } from 'src/app/services/client-service.service';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css'],
})
export class AddClientComponent implements OnInit {
  registerForm!: FormGroup;
  submitted: boolean = false;
  arrayClient: Array<ClientModel> = [];
  client!: ClientModel;

  constructor(
    private formBuilder: FormBuilder,
    private clientService: ClientServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      client_name: ['', Validators.required],
      client_lastname: ['', Validators.required],
      client_email: ['', Validators.required, Validators.email],
      client_document_type: ['', Validators.required],
      client_document_number: ['', Validators.required],
      client_birthday: ['', Validators.required],
      client_created_at: ['', Validators.required],
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
    this.clientService.addClient(this.registerForm.value).subscribe((resp) => {
      console.log('Respuestaaaaaaaaa: ', resp);
    });
    location.href = '/';
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
