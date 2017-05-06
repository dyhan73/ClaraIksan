package clara.iksan.convert;

/**
 * Convert TPS file which is used "얼마에요? 2000" to CSV file.
 * Original code from github.
 * 
 * I modified to fit to my purpose (deleting unused arguments and fit to specific files)
 */


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import nl.cad.tpsparse.CharConverter;
import nl.cad.tpsparse.convert.AbstractTpsToCsv;
import nl.cad.tpsparse.convert.BufferingTpsToCsv;
import nl.cad.tpsparse.convert.StreamingTpsToCsv;
import nl.cad.tpsparse.csv.BufferingCsvWriter;
import nl.cad.tpsparse.csv.CsvWriter;
import nl.cad.tpsparse.csv.ImmediateCsvWriter;
import nl.cad.tpsparse.tps.NotATopSpeedFileException;
import nl.cad.tpsparse.tps.TpsFile;
import nl.cad.tpsparse.tps.record.FieldDefinitionRecord;
import nl.cad.tpsparse.tps.record.TableDefinitionRecord;
import nl.cad.tpsparse.util.Utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Converts TPS files into CSV files. Also displays various information on a TPS
 * file.
 * @author E.Hooijmeijer
 */
public class Tps2Csv {

    /**
     * JCommander style commandline parameters.
     */
    public static class Args {
        @Parameter(names = "-s", description = "source TPS file or folder containing TPS files.", converter = FileConverter.class, required = false)
        private File sourceFile;
        @Parameter(names = "-t", description = "target CSV file or folder to create CSV files in.", converter = FileConverter.class, required = false)
        private File targetFile;
        @Parameter(names = "-sep", description = "separator character, used to separate fields. Use two hex digits for non standard chars (09=tab).", required = false, converter = CharConverter.class)
        private char separator = ',';
        @Parameter(names = "-quot", description = "quote character, used to quote field values. Use two hex digits for non standard chars.", required = false, converter = CharConverter.class)
        private char quoteCharacter = '\"';
        @Parameter(names = "-ignoreErrors", description = "ignores errors, parsing only the pages that are readable (data is lost!)", required = false)
        private boolean ignoreErrors;
        @Parameter(names = { "-?", "-help", "--help" }, description = "displays help and usage information.", required = false)
        private boolean help;
        @Parameter(names = { "-encoding" }, description = "CSV output encoding.", required = false)
        private String encoding = "ISO-8859-1";
        @Parameter(names = { "-tpsEncoding" }, description = "TPS (input) encoding for strings.", required = false)
        private String tpsEncoding = "ISO-8859-1";
        @Parameter(names = { "-raw" }, description = "Don't attempt any character encoding, output the bytes as is.")
        private boolean raw = false;
        @Parameter(names = { "-direct" }, description = "writes directly to file without buffering, useful for large files. Doesn't sort.")
        private boolean direct = false;
        @Parameter(names = { "-verbose" }, description = "more verbose output.")
        private boolean verbose = false;
    }

    public boolean convertAll(String tpsPath, String dataPath) {
        Args params = new Args();
        JCommander cmd = new JCommander(params);

        params.encoding = "UTF-8";
        params.tpsEncoding = "CP949";

        try {
            params.sourceFile = new File(tpsPath + "/MG001.TPS");
            params.targetFile = new File(dataPath + "/csv/subclass.csv");
            parseFile(params);
            System.out.println("Success : MG001.TPS to subclass.csv");

            params.sourceFile = new File(tpsPath + "/MF001.TPS");
            params.targetFile = new File(dataPath + "/csv/class.csv");
            parseFile(params);
            System.out.println("Success : MF001.TPS to class.csv");

            params.sourceFile = new File(tpsPath + "/MB001.TPS");
            params.targetFile = new File(dataPath + "/csv/account_book.csv");
            parseFile(params);
            System.out.println("Success : MB001.TPS to account_book.csv");

            params.sourceFile = new File(tpsPath + "/MH001.TPS");
            params.targetFile = new File(dataPath + "/csv/category.csv");
            parseFile(params);
            System.out.println("Success : MH001.TPS to category.csv");

            params.sourceFile = new File(tpsPath + "/PHONE.TPS");
            params.targetFile = new File(dataPath + "/csv/member_detail.csv");
            parseFile(params);
            System.out.println("Success : PHONE.TPS to member_detail.csv");

            params.sourceFile = new File(tpsPath + "/MA001.TPS");
            params.targetFile = new File(dataPath + "/csv/bank_account.csv");
            parseFile(params);
            System.out.println("Success : MA001.TPS to bank_account.csv");

            params.sourceFile = new File(tpsPath + "/MBOOK.TPS");
            params.targetFile = new File(dataPath + "/csv/donation_book.csv");
            parseFile(params);
            System.out.println("Success : MBOOK.TPS to donation_book.csv");

            params.sourceFile = new File(tpsPath + "/MAN.TPS");
            params.targetFile = new File(dataPath + "/csv/member.csv");
            parseFile(params);
            System.out.println("Success : MAN.TPS to member.csv");
            System.out.println("Completed!");


        } catch (Exception e) {
            System.out.println("Failure!!");
            return false;
        }
        
        return true;
    }

    /**
     * @param folder the folder to scan.
     * @return the tps files in the folder.
     */
    private static File[] listFiles(File folder) {
        return folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().toLowerCase().endsWith(".tps");
            }
        });
    }

    /**
     * processes a single file.
     * @param args the commandline arguments.
     * @throws IOException if reading/writing fails.
     */
    private static void parseFile(Args args) throws IOException {
        TpsFile tpsFile = openFile(args);
        //
        try {
            Map<Integer, TableDefinitionRecord> tableDefinitions = tpsFile.getTableDefinitions(args.ignoreErrors);
            if (args.verbose) {
                System.out.println("Memory: " + Utils.reportMemoryUsage());
            }
            //
            if (args.targetFile != null) {
                //
                for (Entry<Integer, TableDefinitionRecord> table : tableDefinitions.entrySet()) {
                    //
                    CsvWriter csv = openOutputCsvFile(args, tableDefinitions, table);
                    try {
                        AbstractTpsToCsv tpsToCsv = null;
                        if (args.direct) {
                            tpsToCsv = new StreamingTpsToCsv(args.sourceFile, args.targetFile, csv, tpsFile, table);
                        } else {
                            tpsToCsv = new BufferingTpsToCsv(args.sourceFile, args.targetFile, csv, tpsFile, table);
                        }
                        tpsToCsv.setIgnoreErrors(args.ignoreErrors);
                        tpsToCsv.setVerbose(args.verbose);
                        tpsToCsv.run();
                    } finally {
                        finishCsvFile(args, tableDefinitions, table, csv);
                    }
                    //
                }
            }
        } catch (Exception ex) {
            System.err.println(args.sourceFile.getName() + " : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private static CsvWriter openOutputCsvFile(Args args, Map<Integer, TableDefinitionRecord> tableDefinitions, Entry<Integer, TableDefinitionRecord> table)
            throws IOException {
        CsvWriter csv = null;
        if (args.direct) {
            if (tableDefinitions.size() == 1) {
                csv = new ImmediateCsvWriter(args.separator, args.quoteCharacter, args.targetFile, args.encoding);
            } else {
                csv = new ImmediateCsvWriter(args.separator, args.quoteCharacter, buildTargetFile(args, table), args.encoding);
            }
        } else {
            csv = new BufferingCsvWriter(args.separator, args.quoteCharacter);
        }
        return csv;
    }

    private static void finishCsvFile(Args args, Map<Integer, TableDefinitionRecord> tableDefinitions, Entry<Integer, TableDefinitionRecord> table,
                                      CsvWriter csv) throws IOException {
        if (csv instanceof BufferingCsvWriter) {
            if (tableDefinitions.size() == 1) {
                if (args.raw) {
                    ((BufferingCsvWriter) csv).writeRaw(args.targetFile);
                } else {
                    ((BufferingCsvWriter) csv).writeToFile(args.targetFile, args.encoding);
                }
            } else {
                File target = buildTargetFile(args, table);
                if (args.raw) {
                    ((BufferingCsvWriter) csv).writeRaw(target);
                } else {
                    ((BufferingCsvWriter) csv).writeToFile(target, args.encoding);
                }
            }
        } else {
            ((ImmediateCsvWriter) csv).close();
        }
    }

    private static File buildTargetFile(Args args, Entry<Integer, TableDefinitionRecord> table) {
        File parentFile = args.targetFile.getParentFile();
        String name = args.targetFile.getName();
        File target = new File(parentFile, name.substring(0, name.lastIndexOf('.')) + "." + getTableName(table) + ".csv");
        return target;
    }

    private static String getTableName(Entry<Integer, TableDefinitionRecord> table) {
        List<FieldDefinitionRecord> fields = table.getValue().getFields();
        if (fields.size() > 0) {
            return fields.get(0).getTableName();
        } else {
            return String.valueOf(table.getKey());
        }
    }

    private static TpsFile openFile(Args args) throws IOException {
        try {
            if (args.verbose) {
                System.out.println("Opening " + args.sourceFile);
            }
            TpsFile tpsFile = new TpsFile(args.sourceFile);
            tpsFile.setStringEncoding(Charset.forName(args.tpsEncoding));
            tpsFile.getHeader();
            return tpsFile;
        } catch (NotATopSpeedFileException ex) {
            throw ex;
        }
    }

}