// $Id:
// FORESTER -- software libraries and applications
// for evolutionary biology research and applications.
//
// Copyright (C) 2008-2009 Christian M. Zmasek
// Copyright (C) 2008-2009 Burnham Institute for Medical Research
// All rights reserved
// 
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA
//
// Contact: phylosoft @ gmail . com
// WWW: www.phylosoft.org/forester

package org.forester.application;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.forester.io.parsers.PhylogenyParser;
import org.forester.phylogeny.Phylogeny;
import org.forester.phylogeny.PhylogenyMethods;
import org.forester.phylogeny.PhylogenyNode;
import org.forester.phylogeny.factories.ParserBasedPhylogenyFactory;
import org.forester.phylogeny.factories.PhylogenyFactory;
import org.forester.util.ForesterUtil;

public class printAllSpecies {

    public static void main( final String args[] ) {
        Phylogeny tree = null;
        PhylogenyNode node = null;
        PrintWriter out = null;
        File infile = null, outfile = null;
        if ( args.length != 2 ) {
            System.err.println( "\nprintAllSpecies: Wrong number of arguments." );
            System.err.println( "Usage: \"java printAllSpecies <infile> <outfile>\"\n" );
            System.exit( -1 );
        }
        infile = new File( args[ 0 ] );
        outfile = new File( args[ 1 ] );
        try {
            final PhylogenyFactory factory = ParserBasedPhylogenyFactory.getInstance();
            final PhylogenyParser pp = ForesterUtil.createParserDependingOnFileType( infile, true );
            tree = factory.create( infile, pp )[ 0 ];
        }
        catch ( final Exception e ) {
            System.err.println( e + "\nCould not read " + infile + "\n" );
            System.exit( -1 );
        }
        node = tree.getFirstExternalNode();
        try {
            out = new PrintWriter( new FileWriter( outfile ), true );
            while ( node != null ) {
                out.println( PhylogenyMethods.getSpecies( node ) );
                node = node.getNextExternalNode();
            }
        }
        catch ( final Exception e ) {
            System.err.println( e + "\nException during writing.\n" );
            System.exit( -1 );
        }
        finally {
            out.close();
        }
    }
}