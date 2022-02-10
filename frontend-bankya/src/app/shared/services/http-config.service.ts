import { Router } from '@angular/router';
import { GlobalService } from './global.service';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class HttpConfigService implements HttpInterceptor {
  constructor(private globalService: GlobalService, private router: Router) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const token: string =
      this.globalService.user && this.globalService.user.jwt;

    let request = req;

    if (token) {
      request = req.clone({
        setHeaders: {
          Authorization: token,
        },
      });
      console.log(token);
    }

    return next.handle(request).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 403) {
          this.router.navigateByUrl('/customers');
        }
        return throwError(error);
      })
    );
  }
}
