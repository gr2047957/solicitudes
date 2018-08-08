import { Moment } from 'moment';

export interface ISolicitudes {
    id?: number;
    id_solicitudes?: string;
    fecha_Registro?: Moment;
    solicitante?: string;
    tipoSolicitud?: string;
    personaASustituir?: string;
    direccion?: string;
    unidadNegocio?: string;
    ceco?: string;
    reportaA?: string;
    servicio?: string;
    periodosAsignacion?: string;
    nombreCandidatoPropuesto?: string;
    apellidosCandidatoPropuesto?: string;
    proveedorCandidatoPropuesto?: string;
    disponibilidadViajar?: boolean;
    experienciaSectorBancario?: boolean;
    nivelBancario?: string;
    prioridad?: number;
    tipoRecurso?: string;
    experienciaBancaria?: string;
    skillTecnico?: string;
    obligatorio?: string;
    experiencia?: string;
    softSkill?: string;
    nivelSoftSkill?: string;
}

export class Solicitudes implements ISolicitudes {
    constructor(
        public id?: number,
        public id_solicitudes?: string,
        public fecha_Registro?: Moment,
        public solicitante?: string,
        public tipoSolicitud?: string,
        public personaASustituir?: string,
        public direccion?: string,
        public unidadNegocio?: string,
        public ceco?: string,
        public reportaA?: string,
        public servicio?: string,
        public periodosAsignacion?: string,
        public nombreCandidatoPropuesto?: string,
        public apellidosCandidatoPropuesto?: string,
        public proveedorCandidatoPropuesto?: string,
        public disponibilidadViajar?: boolean,
        public experienciaSectorBancario?: boolean,
        public nivelBancario?: string,
        public prioridad?: number,
        public tipoRecurso?: string,
        public experienciaBancaria?: string,
        public skillTecnico?: string,
        public obligatorio?: string,
        public experiencia?: string,
        public softSkill?: string,
        public nivelSoftSkill?: string
    ) {
        this.disponibilidadViajar = false;
        this.experienciaSectorBancario = false;
    }
}
