import { User } from "./User";
export enum StatusRec{
    EnAttente,Valide,Refuse
}
export class Reclamation{
    id!:number;
    description!:string;
    date!:Date;
    creator:User  |undefined;
    category!:string;
    status!:StatusRec;
}