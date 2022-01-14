import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statePipe',
})
export class StatePipePipe implements PipeTransform {
  public message: string = '';

  transform(value: string, ...args: unknown[]) {
    if (value === 'A') {
      this.message = 'Activo';
    }
    if (value === 'I') {
      this.message = 'Ictivo';
    }
    if (value === 'C') {
      this.message = 'Cancelado';
    }
    return this.message;
  }
}
