import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { UserService } from 'src/app/security/services/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { UserDto } from 'src/app/security/models/user.dto';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  state: boolean = false;
  submitted: boolean = false;
  registerForm!: FormGroup;

  user: UserDto = {
    username: '',
    password: '',
    jwt: '',
    lastname: '',
    name: '',
  };

  constructor(
    private userService: UserService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      name: ['', Validators.required],
      lastname: ['', Validators.required],
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
    this.userService.save(this.registerForm.value).subscribe(
      (resp) => {
        if (resp.success) {
          alert(resp.messageResult);
        }
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
    this.router.navigateByUrl('/');
  }

  onReset() {
    this.submitted = false;
    this.registerForm.reset();
  }
}
