import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from 'src/app/security/services/user.service';
import { UserDto } from 'src/app/security/models/user.dto';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  @Output() login: EventEmitter<UserDto> = new EventEmitter();

  username: string = '';
  password: string = '';

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  signIn() {
    if (this.username && this.password) {
      const user: UserDto = new UserDto();
      user.username = this.username;
      user.password = this.password;

      this.userService.login(user).subscribe(
        (resp) => {
          if (resp.success) {
            this.login.emit(resp.data);
            alert(resp.message);
          }
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    } else {
      alert('Clave o usuario incorrectos');
    }
  }
}
