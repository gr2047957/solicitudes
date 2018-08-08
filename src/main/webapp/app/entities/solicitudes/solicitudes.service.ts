import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ISolicitudes } from 'app/shared/model/solicitudes.model';

type EntityResponseType = HttpResponse<ISolicitudes>;
type EntityArrayResponseType = HttpResponse<ISolicitudes[]>;

@Injectable({ providedIn: 'root' })
export class SolicitudesService {
    private resourceUrl = SERVER_API_URL + 'api/solicitudes';

    constructor(private http: HttpClient) {}

    create(solicitudes: ISolicitudes): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(solicitudes);
        return this.http
            .post<ISolicitudes>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(solicitudes: ISolicitudes): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(solicitudes);
        return this.http
            .put<ISolicitudes>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ISolicitudes>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ISolicitudes[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(solicitudes: ISolicitudes): ISolicitudes {
        const copy: ISolicitudes = Object.assign({}, solicitudes, {
            fecha_Registro:
                solicitudes.fecha_Registro != null && solicitudes.fecha_Registro.isValid()
                    ? solicitudes.fecha_Registro.format(DATE_FORMAT)
                    : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.fecha_Registro = res.body.fecha_Registro != null ? moment(res.body.fecha_Registro) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((solicitudes: ISolicitudes) => {
            solicitudes.fecha_Registro = solicitudes.fecha_Registro != null ? moment(solicitudes.fecha_Registro) : null;
        });
        return res;
    }
}
