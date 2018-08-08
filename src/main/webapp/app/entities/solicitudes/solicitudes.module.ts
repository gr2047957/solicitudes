import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DemoSharedModule } from 'app/shared';
import {
    SolicitudesComponent,
    SolicitudesDetailComponent,
    SolicitudesUpdateComponent,
    SolicitudesDeletePopupComponent,
    SolicitudesDeleteDialogComponent,
    solicitudesRoute,
    solicitudesPopupRoute
} from './';

const ENTITY_STATES = [...solicitudesRoute, ...solicitudesPopupRoute];

@NgModule({
    imports: [DemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        SolicitudesComponent,
        SolicitudesDetailComponent,
        SolicitudesUpdateComponent,
        SolicitudesDeleteDialogComponent,
        SolicitudesDeletePopupComponent
    ],
    entryComponents: [SolicitudesComponent, SolicitudesUpdateComponent, SolicitudesDeleteDialogComponent, SolicitudesDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DemoSolicitudesModule {}
