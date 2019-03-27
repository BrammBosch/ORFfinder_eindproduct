#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Fri May 18 15:36:31 2018
@author: anton
"""

from Bio.Blast import NCBIWWW
from Bio.Blast import NCBIXML
import time
import re
from Bio import GenBank
from Bio import Entrez
import sys
Entrez.email = 'bm-bosch@hotmail.com'


def main():
    """Blast een sequentie een geeft de input terug
    Input: sequentie in fasta format
    Output: lijst(info) met lijsten(hits) die allemaal informatie bevatten"""
    seq = sys.argv[1]
    blast(seq)
    info = get_info(seq)
    blastBestand = open("/src/ORFfinderGui/blastOutput.txt","w")
    blastBestand.write(str(info))
        

def blast(seq):
    '''geeft parameters aan en blast vervolgens de sequentie, de output wordt verwerkt in een xml-file
    Input: een header en sequentie in de 'seq' string
    Output: schrijft de resultaten weg in een XML-file'''
    result_handle = NCBIWWW.qblast("blastp", "nr", seq, matrix_name="BLOSUM50", hitlist_size=1, 
                                   gapcosts='13 2', word_size=6, filter="T", expect=1e-8)
    with open("my_blast.xml", "w") as out_handle:
         out_handle.write(result_handle.read())

def get_info(seq):
    ''' Haalt de nodige informatie uit de blast-output
    Input: de seq(header en sequentie) en de BLAST output in XML format
    Output: een lijst per sequentie met daarin de nodige informatie per hit'''
    info, ac = [], []
    
    result_handle = open('my_blast.xml')
    blast_record = NCBIXML.read(result_handle)
    
    for alignment in blast_record.alignments:
        for hsp in alignment.hsps:
            
            accessie_code = re.search(r'.*?\|.*?\|.*?\|(.*?)\|.',alignment.title).group(1)
            if accessie_code not in ac:
                print(alignment.title,'\n'*2)
                hit = []
                ac.append(accessie_code)
                hit.append(accessie_code) #accessie code
                hit.append(hsp.expect) #e-value
                hit.append(hsp.identities) #identity
                hit.append(hsp.align_length) #query_cover
                hit.append(hsp.bits) #total-score
                #hit.append(re.search(r'\.[0-9]\| (.*?)\[.',alignment.title).group(1)) #functie
                #organism = get_organism(accessie_code)
                # family, genus, species = get_taxonomy(organism)
                #hit.append(family) #familie
                #hit.append(genus) #geslacht
                #hit.append(species) #soort
                info.append(hit)
            
    result_handle.close()
    
    if info == []:
        info.append(['No hit found for', str(seq.split('\n')[0])])
    
    return info
'''    
def get_organism(accessie_code):
    """Gebruikt de Genbank database om met de accessie code de naam van het organisme te achterhalen
    Input: accessie_code
    Output: naam van het organisme"""
    time.sleep(1)
    handle = Entrez.efetch(db="Protein", id=accessie_code, rettype="gb", retmode="text")
    file = GenBank.read(handle)
    organism = file.organism
    return organism

def get_taxonomy(organism):
    """Gebruikt de naam van het organisme om de taxonomy te verkrijgen van de taxonomy database
    Input: organisme naam
    Output: taxonomy(familie, genus en geslacht)"""
    family, genus, species = 'Not found', 'Not found', 'Not found'
    try:
        time.sleep(1)
        handle = Entrez.esearch(db="Taxonomy", term=organism)
        record = Entrez.read(handle)
        time.sleep(1)
        handle = Entrez.efetch(db="Taxonomy", id=record["IdList"], retmode="xml")
        records = Entrez.read(handle)
        lin = records[0]['LineageEx']
        for dic in lin:
            if dic['Rank'] == 'family':
                family = dic['ScientificName']
            elif dic['Rank'] == 'genus':
                genus = dic['ScientificName']
            elif dic['Rank'] == 'species':
                species = dic['ScientificName']
    except RuntimeError:
        pass
    return family, genus, species
'''

main()
