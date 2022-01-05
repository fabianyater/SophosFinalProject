import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ClientsComponent } from './clients/clients.component';

@NgModule({
  declarations: [ClientsComponent],
  imports: [CommonModule, HttpClientModule],
})
export class PagesModule {}
