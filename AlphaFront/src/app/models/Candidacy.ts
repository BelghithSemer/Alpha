import { Offer } from "./Offer";
import { User } from "./User";

export class Candidacy{
    id!: number;

    date!:Date;

    
    offer!:Offer;

    
    candidat!:User;


    cv!:string;
}