import { Candidacy } from "./Candidacy";

export enum typeOffre{
    Formation, Stage, Emploi
}
export class Offer{
    id:number | undefined;

    description!:string;
    company!:string;
    type!:typeOffre;

    duree!:number;
    date!:Date;
    candidacies:Candidacy[]=[];
}