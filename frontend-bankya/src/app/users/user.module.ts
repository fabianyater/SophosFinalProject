import { ProfileComponent } from './components/profile/profile.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AppRoutingModule } from '../app-routing.module';

@NgModule({
  declarations: [ProfileComponent],
  imports: [CommonModule, AppRoutingModule],
  exports: [ProfileComponent],
})
export class UserModule {}
