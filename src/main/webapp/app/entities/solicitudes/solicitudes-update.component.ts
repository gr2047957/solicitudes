import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ISolicitudes } from 'app/shared/model/solicitudes.model';
import { SolicitudesService } from './solicitudes.service';
import moment = require("moment");

@Component({
    selector: 'jhi-solicitudes-update',
    templateUrl: './solicitudes-update.component.html'
})
export class SolicitudesUpdateComponent implements OnInit {
    private _solicitudes: ISolicitudes;
    isSaving: boolean;
    fecha_RegistroDp: any;

    constructor(private solicitudesService: SolicitudesService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ solicitudes }) => {
            this.solicitudes = solicitudes;
            this.solicitudes.fecha_Registro = moment();
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.solicitudes.id !== undefined) {
            this.subscribeToSaveResponse(this.solicitudesService.update(this.solicitudes));
        } else {
            this.subscribeToSaveResponse(this.solicitudesService.create(this.solicitudes));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ISolicitudes>>) {
        result.subscribe((res: HttpResponse<ISolicitudes>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get solicitudes() {
        return this._solicitudes;
    }

    set solicitudes(solicitudes: ISolicitudes) {
        this._solicitudes = solicitudes;
    }
}
