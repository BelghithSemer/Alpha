export enum typeOffre{
    Formation, Stage, Emploi
}
export class Offer{
    id!:number;

    description!:string;
    company!:string;
    type!:typeOffre;

    duree!:number;
    date!:Date;
}