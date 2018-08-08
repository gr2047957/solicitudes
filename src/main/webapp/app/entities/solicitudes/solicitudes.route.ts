import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { Solicitudes } from 'app/shared/model/solicitudes.model';
import { SolicitudesService } from './solicitudes.service';
import { SolicitudesComponent } from './solicitudes.component';
import { SolicitudesDetailComponent } from './solicitudes-detail.component';
import { SolicitudesUpdateComponent } from './solicitudes-update.component';
import { SolicitudesDeletePopupComponent } from './solicitudes-delete-dialog.component';
import { ISolicitudes } from 'app/shared/model/solicitudes.model';

@Injectable({ providedIn: 'root' })
export class SolicitudesResolve implements Resolve<ISolicitudes> {
    constructor(private service: SolicitudesService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((solicitudes: HttpResponse<Solicitudes>) => solicitudes.body));
        }
        return of(new Solicitudes());
    }
}

export const solicitudesRoute: Routes = [
    {
        path: 'solicitudes',
        component: SolicitudesComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'demoApp.solicitudes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solicitudes/:id/view',
        component: SolicitudesDetailComponent,
        resolve: {
            solicitudes: SolicitudesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.solicitudes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solicitudes/new',
        component: SolicitudesUpdateComponent,
        resolve: {
            solicitudes: SolicitudesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.solicitudes.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'solicitudes/:id/edit',
        component: SolicitudesUpdateComponent,
        resolve: {
            solicitudes: SolicitudesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.solicitudes.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const solicitudesPopupRoute: Routes = [
    {
        path: 'solicitudes/:id/delete',
        component: SolicitudesDeletePopupComponent,
        resolve: {
            solicitudes: SolicitudesResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'demoApp.solicitudes.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
