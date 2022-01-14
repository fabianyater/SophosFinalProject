import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { ClientsComponent } from './list-clients/clients.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { AddClientComponent } from './add-client/add-client.component';
import { ReactiveFormsModule } from '@angular/forms';
import { StatePipePipe } from 'src/app/pipes/state-pipe.pipe';

const routes: Routes = [
  {
    path: '',
    component: ClientsComponent,
    children: [
      { path: '', redirectTo: 'clients', pathMatch: 'full' },
      { path: 'clients/add', component: AddClientComponent },
    ],
  },
  { path: 'client/:id/products', component: ClientDetailsComponent },
];

@NgModule({
  declarations: [ClientsComponent, ClientDetailsComponent, AddClientComponent, StatePipePipe],
  imports: [CommonModule, RouterModule.forChild(routes), ReactiveFormsModule],
})
export class ClientModule {}
