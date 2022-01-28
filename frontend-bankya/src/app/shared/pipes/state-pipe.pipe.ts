import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statePipe',
})
export class StatePipePipe implements PipeTransform {
  public message: string = '';

  transform(value: string, ...args: unknown[]) {
    if (value === 'A') {
      this.message = 'Active';
    }
    if (value === 'I') {
      this.message = 'Inactive';
    }
    if (value === 'C') {
      this.message = 'Canceled';
    }
    return this.message;
  }
}
