import { UserDto } from 'src/app/security/models/user.dto';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GlobalService {

  public user: UserDto = new UserDto();

  constructor() { }
}
