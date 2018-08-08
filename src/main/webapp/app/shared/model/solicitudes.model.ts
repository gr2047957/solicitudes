import { Moment } from 'moment';

export interface ISolicitudes {
    id?: number;
    id_solicitudes?: string;
    nombre_corto?: string;
    apellido?: string;
    fecha_Registro?: Moment;
}

export class Solicitudes implements ISolicitudes {
    constructor(
        public id?: number,
        public id_solicitudes?: string,
        public nombre_corto?: string,
        public apellido?: string,
        public fecha_Registro?: Moment
    ) {}
}
